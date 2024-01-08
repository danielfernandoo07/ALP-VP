<?php

namespace App\Http\Controllers;

use App\Models\Photo;
use App\Models\UserImage;
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

    public function getUserImage(){
        $user = Auth::user();
        $userImage = UserImage::where('user_id', $user->id)->first();
        return $userImage;
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

    public function showSpecificOtherProfile($id)
    {
        $user = User::with('content')->findOrFail($id);
        return $user;
    }

    public function updateImage()
    {
        $user = Auth::user();
        if (!$user) {
            return [
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "User not authenticated",
                'data' => []
            ];
        }
        if (!empty($user)) {
            try {
                $userImage = UserImage::where('user_id', $user->id)->first();
                $user->photo = $userImage->image;
                $user->save();
                return [
                    'status' => Response::HTTP_OK,
                    'message' => "Success update image",
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

        if (!empty($user)) {
            try {
                $oldData = [
                    'name' => $user->name,
                    'password' => $user->password,
                    'bio' => $user->bio
                ];
                if ($request->name) {
                    $user->name = $request->name;
                } else {
                    $user->name = $oldData['name'];
                }

                if ($request->password) {
                    $user->password = Hash::make($request->password);
                } else {
                    $user->password = $oldData['password'];
                }

                if ($request->bio) {
                    $user->bio = $request->bio;
                } else {
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