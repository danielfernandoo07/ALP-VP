<?php

namespace App\Http\Controllers;

use App\Models\Content;
use Exception;
use Carbon\Carbon;
use App\Models\Comment;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use App\Http\Requests\StoreCommentRequest;
use App\Http\Requests\UpdateCommentRequest;
use Symfony\Component\HttpFoundation\Response;

class CommentController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $comment = Comment::all();
        return response()->json($comment);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create(Request $request)
    {
        $validated = $request->validate([
            'content_id' => 'required|exists:contents,id',
            'comment_text' => 'required'
        ]);

        try {
            $comment = new Comment();
            $comment->comment_text = $request->comment_text;
            $comment->content_id = $request->content_id;
            $comment->user_id = Auth::user()->id;
            $comment->created_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $comment->updated_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $comment->save();
            return $comment->loadMissing('user:id,name');
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function showCommentsbyContent($content_id)
    {
        $comments = Comment::where('content_id', $content_id)->get();
        return $comments->loadMissing('user:id,name');
    }

    public function update(Request $request, $id)
    {
        $validated = $request->validate([
            'comment_text' => 'required'
        ]);
        try {
            $comment = Comment::findorFail($id);
            $comment->comment_text = $request->comment_text;
            $comment->updated_at = Carbon::now()->timezone('Asia/Jakarta')->format('Y-m-d H:i:s');
            $comment->save();
            return $comment->loadMissing('user:id,name');
        } catch (Exception $e) {
            return [
                'status' => Response::HTTP_INTERNAL_SERVER_ERROR,
                'message' => $e->getMessage(),
                'data' => []
            ];
        }
    }

    public function delete($id)
    {
        try {
            $comment = Comment::findorFail($id);
            $comment->delete();
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
