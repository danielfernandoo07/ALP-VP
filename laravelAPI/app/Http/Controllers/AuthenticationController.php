<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Validation\ValidationException;
use Symfony\Component\HttpFoundation\Response;

class AuthenticationController extends Controller
{
    public function login(Request $request)
    {
        $request->validate([
            'email' => 'required|email',
            'password' => 'required',
        ]);
        $user = User::where('email', $request->email)->first();
        
        if(!empty($user)){
            if(Hash::check($request->password, $user->password)){
                return[
                    'status' => Response::HTTP_OK,
                    'message' => "Token Created",
                    'data' => $user->createToken($request->email)->plainTextToken
                ];
            }else{
                return[
                    'status' => Response::HTTP_FORBIDDEN,
                    'message' => "Incorrect Password",
                    'data' => []
                ];
            }
        }else{
            return[
                'status' => Response::HTTP_NOT_FOUND,
                'message' => "User not found",
                'data' => []
            ];
        }
    }

    public function logout(Request $request){

        $request->user()->currentAccessToken()->delete();

        return[
            'status' => Response::HTTP_OK,
            'message' => "Token Deleted",
            'data' => []
        ];

    }
}
