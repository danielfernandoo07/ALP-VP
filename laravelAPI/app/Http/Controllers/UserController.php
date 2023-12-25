<?php

namespace App\Http\Controllers\Api;

use App\Http\Controllers\Controller;
use App\Http\Resources\UserResource;
use App\Models\User;
use Exception;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Symfony\Component\HttpFoundation\Response;

class UserController extends Controller
{
    public function getAllUser()
    {
        $users = User::all();
        return UserResource::collection($users);
    }

    public function checkPassword()
    {
        $users = User::all();
        $check = [];

        foreach ($users as $user) {
            array_push($check,
                Hash::check("Evan1", $user->password));
        }
        return $check;
    }

    public function createUser(Request $request)
    {
        $validated = $request->validate([
            'name' => 'required',
            'email' => 'required',
            'nim' => 'required',
            'password' => 'required',
            'prodi_id' => 'required'
        ]);
        try {
            $user = new User();
            $user->name = $request->name;
            $user->email = $request->email;
            $user->password = Hash::make($request->password);
            $user->nim = $request->nim;
            $user->photo = $request->photo;
            $user->bio = $request->bio;
            $user->prodi_id = $request->prodi_id;
            $user->save();
            return [
                'status' => Response::HTTP_OK,
                'message' => "Success",
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

    public function updateUser(Request $request)
    {
        if (!empty($request->email)) {
            $user = User::where('email', $request->email)->first();
        } else {
            $user = User::where('id', $request->id)->first();
        }

        if (!empty($user)) {
            try {
                $user->name = $request->name;
                $user->email = $request->email;
                $user->password = Hash::make($request->password);
                $user->nim = $request->nim;
                $user->photo = $request->photo;
                $user->bio = $request->bio;
                $user->prodi_id = $request->prodi_id;
                $user->save();
                return [
                    'status' => Response::HTTP_OK,
                    'message' => "Success",
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

        return [
            'status' => Response::HTTP_NOT_FOUND,
            'message' => "User not found",
            'data' => []
        ];
    }

    public function deleteUser(Request $request)
    {
        if (!empty($request->email)) {
            $user = User::where('email', $request->email)->first();
        } else {
            $user = User::where('id', $request->id)->first();
        }

        if (!empty($user)) {
            $user->delete();

            return [
                'status' => Response::HTTP_OK,
                'message' => "Success",
                'data' => []
            ];
        }

        return [
            'status' => Response::HTTP_NOT_FOUND,
            'message' => "User not found",
            'data' => []
        ];
    }
}
