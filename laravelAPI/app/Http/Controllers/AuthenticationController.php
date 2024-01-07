<?php

namespace App\Http\Controllers;

use Exception;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Storage;
use Illuminate\Validation\ValidationException;
use Symfony\Component\HttpFoundation\Response;

class AuthenticationController extends Controller
{

    public function register(Request $request)
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
            if ($request->file) {
                $photo = $request->file;
                $photoName = time() . '.' . $photo->extension();
                $photo->move(public_path('photo'), $photoName);
                $user->photo = 'https://alpvp.shop/photos/' . $photoName;
            } 
            else {
                $user->photo = 'https://yourteachingmentor.com/wp-content/uploads/2020/12/istockphoto-1223671392-612x612-1.jpg';
            }
            $user->nim = $request->nim;
            $user->bio = $request->bio;
            $user->prodi_id = $request->prodi_id;
            $user->save();
            return [
                'status' => Response::HTTP_OK,
                'message' => "Success",
                'pengguna' => $user
            ];
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'pengguna' => []
            ];
        }
    }

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