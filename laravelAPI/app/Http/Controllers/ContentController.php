<?php

namespace App\Http\Controllers;

use Exception;
use Carbon\Carbon;
use App\Models\Content;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\File;
use App\Http\Resources\ContentResource;
use Illuminate\Support\Facades\Storage;
use App\Http\Requests\StoreContentRequest;
use App\Http\Requests\UpdateContentRequest;
use App\Http\Resources\ContentDetailResource;
use Symfony\Component\HttpFoundation\Response;

class ContentController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $contents = Content::all();
        // return response()->json($content);
        return $contents->loadMissing('user:id,name,photo');
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(Request $request)
    {
        $validated = $request->validate([
            'headline' => 'required',
            'content_text' => 'required',
            'category_id' => 'required',
        ]);

        try {
            $content = new Content();
            $content->headline = $request->headline;
            if ($request->file) {
                $image = $request->file;
                $imageName = time() . '.' . $image->extension();
                $image->move(public_path('images'), $imageName);
                $content->image = 'https://alpvp.shop/images/' . $imageName;
            } else {
                $content->image = null;
            }
            $content->content_text = $request->content_text;
            $content->category_id = $request->category_id;
            $content->created_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $content->updated_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $content->user_id = Auth::user()->id;
            $content->save();
            return $content->loadMissing('user:id,name,photo');
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function showImage($id){
        $content = Content::findOrFail($id);
        $path = public_path('images') . '/' . $content->image;
        return response()->file($path);
    }

    public function show($id)
    {
        $content = Content::with('user:id,name')->findOrFail($id);
        $content->created_at_formatted = $content->created_at;
        $content->updated_at_formatted = $content->updated_at;
        return $content->loadMissing('user:id,name,photo');
    }

    public function showWithComments($id)
    {
        $content = Content::with('user:id,name', 'comment')->findOrFail($id);
        $content->created_at_formatted = $content->created_at;
        $content->updated_at_formatted = $content->updated_at;
        return $content->loadMissing('user:id,name,photo');
    }

    public function updateImage(Request $request, $id)
    {
        $validated = $request->validate([
            'file' => 'required|image|mimes:jpeg,png,jpg,gif|max:2048',
        ]);

        try {
            $content = Content::findOrFail($id);
            $oldData = [
                'image' => $content->image,
            ];
            if ($request->file) {
                $oldImagePath = public_path('images') . '/' . $content->image;
                if (File::exists($oldImagePath)) {
                    File::delete($oldImagePath);
                }
            
                $image = $request->file;
                if ($image && $image->isValid()) {
                    $imageName = time() . '.' . $image->extension();
                } else {
                   return [
                        'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                        'message' => "Image is not valid",
                        'data' => []
                    ];
                }
                $image->move(public_path('images'), $imageName);
                $content->image = 'https://alpvp.shop/images/' . $imageName;
            } else {
                $content->image = $oldData['image'];
                return [
                    'status' => Response::HTTP_OK,
                    'message' => "No image uploaded",
                    'data' => $content->loadMissing('user:id,name,photo')
                ];
            }

            $content->save();
            return $content->loadMissing('user:id,name,photo');
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'headline' => 'sometimes|required',
            'content_text' => 'sometimes|required',
            'category_id' => 'sometimes|required'
        ]);

        try {
            $content = Content::findOrFail($id);
            $oldData = [
                'headline' => $content->headline,
                'content_text' => $content->content_text,
                'category_id' => $content->category_id,
            ];
            if ($request->headline) {
                $content->headline = $request->headline;
            } else {
                $content->headline = $oldData['headline'];
            }
            
            if ($request->content_text) {
                $content->content_text = $request->content_text;
            } else {
                $content->content_text = $oldData['content_text'];
            }

            if ($request->category_id) {
                $content->category_id = $request->category_id;
            } else {
                $content->category_id = $oldData['category_id'];
            }
            $content->user_id = Auth::user()->id;
            $content->updated_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $content->save();
            return $content->loadMissing('user:id,name,photo');
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function contentsByUser($userId)
    {
        $contents = Content::where('user_id', $userId)->get();
        return $contents->loadMissing('user:id,name,photo');
    }

    public function delete($id)
    {
        try {
            $content = Content::findorFail($id);
            $content->delete();
            return [
                'status' => Response::HTTP_OK,
                'message' => "Success",
                'data' => []
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