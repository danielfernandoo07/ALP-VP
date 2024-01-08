<?php

namespace App\Http\Controllers;

use Exception;
use App\Models\UserImage;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Http\Requests\StoreUserImageRequest;
use App\Http\Requests\UpdateUserImageRequest;
use Symfony\Component\HttpFoundation\Response;

class UserImageController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        //
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(Request $request)
    {
        $user = Auth::user();

        if (!$user) {
            return [
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "User not authenticated",
                'data' => []
            ];
        }

        // $validated = $request->validate([
        //     'file' => 'required|image|mimes:jpeg,png,jpg,gif|max:2048',
        // ]);

        try {
            $oldImage = UserImage::where('user_id', $user->id)->first();

            if ($request->file) {
                $userImage = new UserImage();
                $userImage->user_id = $user->id;
                if ($oldImage) {
                    $oldImage->delete();
                }
                $image = $request->file;
                $imageName = time() . '.' . $image->extension();
                $image->move(public_path('photos'), $imageName);
                $userImage->image = 'https://alpvp.shop/photos/' . $imageName;
            } else {
                return [
                    'status' => Response::HTTP_BAD_REQUEST,
                    'message' => "Image not found",
                    'data' => []
                ];
            }
            $userImage->save();
            return $userImage;
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreUserImageRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show(UserImage $userImage)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(UserImage $userImage)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateUserImageRequest $request, UserImage $userImage)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(UserImage $userImage)
    {
        //
    }
}
