<?php

namespace App\Http\Controllers;

use Exception;
use Carbon\Carbon;
use App\Models\Content;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
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
        return $contents->loadMissing('user:id,name');
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
                $filename = $this->generateRandomString();
                $extension = $request->file->extension();

                Storage::putFileAs('image', $request->file, $filename . '.' . $extension);
                $content->image = $filename . '.' . $extension;
            } 
            else{
                $content->image = null;
            }
            $content->content_text = $request->content_text;
            $content->category_id = $request->category_id;
            $content->created_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $content->updated_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $content->user_id = Auth::user()->id;
            $content->save();
            return $content->loadMissing('user:id,name');
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function show($id)
    {
        $content = Content::with('user:id,name')->findOrFail($id);
        $content->created_at_formatted = $content->created_at;
        $content->updated_at_formatted = $content->updated_at;
        return $content->loadMissing('user:id,name');
    }

    public function showWithComments($id)
    {
        $content = Content::with('user:id,name', 'comment')->findOrFail($id);
        $content->created_at_formatted = $content->created_at;
        $content->updated_at_formatted = $content->updated_at;
        return $content->loadMissing('user:id,name');
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'headline' => 'required',
            'content_text' => 'required',
            'category_id' => 'required',
        ]);

        try {
            $content = Content::findOrFail($id);
            $content->headline = $request->headline;
            $content->image = $request->image;
            $content->content_text = $request->content_text;
            $content->category_id = $request->category_id;
            $content->user_id = Auth::user()->id;
            $content->updated_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $content->save();
            return $content->loadMissing('user:id,name');
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
        return $contents->loadMissing('user:id,name');
    }

    public function delete($id){
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

    function generateRandomString($length = 30) {
        $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $charactersLength = strlen($characters);
        $randomString = '';
        for ($i = 0; $i < $length; $i++) {
            $randomString .= $characters[random_int(0, $charactersLength - 1)];
        }
        return $randomString;
    }
}
