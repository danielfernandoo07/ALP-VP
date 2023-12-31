<?php

namespace App\Http\Controllers;

use Exception;
use App\Models\User;
use Illuminate\Http\Request;
use App\Http\Controllers\Controller;
use App\Http\Resources\UserResource;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;
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
            'photo' => 'sometimes|required',
            'bio' => 'sometimes|required',
        ]);

        try {
            if (isset($validatedData['name'])) {
                $user->name = $validatedData['name'];
            }
    
            if (isset($validatedData['password'])) {
                $user->password = Hash::make($validatedData['password']);
            }
    
            if (isset($validatedData['photo'])) {
                $user->photo = $validatedData['photo'];
            }
    
            if (isset($validatedData['bio'])) {
                $user->bio = $validatedData['bio'];
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

    public function delete(Request $request)
    {
        $user = Auth::user();

        if (!$user) {
            return [
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "User not authenticated",
                'data' => []
            ];
        }else{
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
