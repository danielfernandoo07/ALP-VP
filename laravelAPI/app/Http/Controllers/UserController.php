<?php

namespace App\Http\Controllers;

use Exception;
use App\Models\User;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Http\Resources\UserResource;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\File;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Storage;
use Symfony\Component\HttpFoundation\Response;

class UserController extends Controller
{
    public function getAllUser()
    {
        $users = User::all();
        return UserResource::collection($users);
    }

    // public function checkPassword()
    // {
    //     $users = User::all();
    //     $check = [];

    //     foreach ($users as $user) {
    //         array_push($check,
    //             Hash::check("Evan1", $user->password));
    //     }
    //     return $check;
    // }
    
    public function showSpecificOtherProfile($id){
        $user = User::with('content')->findOrFail($id);
        return $user;
    }

    public function updateImage(Request $request)
    {
        $user = Auth::user();

        if (!$user) {
            return [
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "User not authenticated",
                'data' => []
            ];
        }
        $validated = $request->validate([
            'photo' => 'required|image|mimes:jpeg,png,jpg,gif|max:2048',
        ]);

        try {
            $oldData = [
                'photo' => $user->photo,
            ];
            if ($request->hasFile('photo')) {
                $oldImagePath = public_path('photos') . '/' . $user->photo;
                if (File::exists($oldImagePath)) {
                    File::delete($oldImagePath);
                }
            
                $photo = $request->file('photo');
                if ($photo && $photo->isValid()) {
                    $photoName = time() . '.' . $photo->extension();
                } else {
                   return [
                        'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                        'message' => "Image is not valid",
                        'data' => []
                    ];
                }
                $photo->move(public_path('photos'), $photoName);
                $user->photo = 'https://alpvp.shop/photos/' . $photoName;
            } else {
                $user->photo = $oldData['photo'];
            }

            $user->save();
            return [
                'status' => Response::HTTP_OK,
                'message' => "User updated successfully",
                'data' => $user
            ];
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function update(Request $request)
    {
        $user = Auth::user();

        if (!$user) {
            return [
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "User not authenticated",
                'data' => []
            ];
        }

        $validatedData = $request->validate([
            'name' => 'sometimes|required',
            'password' => 'sometimes|required',
            'bio' => 'sometimes|required',
        ]);

        if (!empty($user)){
            try {
                $oldData = [
                    'name' => $user->name,
                    'password' => $user->password,
                    'photo' => $user->photo,
                    'bio' => $user->bio,
                ];
                if ($request->name) {
                    $user->name = $request->name;
                } else{
                    $user->name = $oldData['name'];
                }
    
                if ($request->password) {
                    $user->password = Hash::make($request->password);
                } else{
                    $user->password = $oldData['password'];
                }
                
                if ($request->bio) {
                    $user->bio = $request->bio;
                } else{
                    $user->bio = $oldData['bio'];
                }
    
                $user->save();
    
                return [
                    'status' => Response::HTTP_OK,
                    'message' => "User updated successfully",
                    'data' => $user
                ];
            } catch (Exception $e) {
                return [
                    'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                    'message' => $e->getMessage(),
                    'data' => []
                ];
            }
        }
    }

    public function delete(Request $request)
    {
        $user = Auth::user();

        if (!$user) {
            return [
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "User not authenticated",
                'data' => []
            ];
        } else {
            $user->currentAccessToken()->delete();
            $user->delete();
            return [
                'status' => Response::HTTP_OK,
                'message' => "Success",
                'data' => []
            ];
        }
    }
}