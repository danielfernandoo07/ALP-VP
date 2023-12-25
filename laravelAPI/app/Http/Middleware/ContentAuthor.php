<?php

namespace App\Http\Middleware;

use Closure;
use App\Models\Content;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Auth;
use Symfony\Component\HttpFoundation\Response;

class ContentAuthor
{
    /**
     * Handle an incoming request.
     *
     * @param  \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response)  $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        $currentUser = Auth::user();
        $content = Content::findOrFail($request->id);

        if ($currentUser->id != $content->user_id) {
            return response()->json([
                'status' => Response::HTTP_UNAUTHORIZED,
                'message' => "You are not authorized to access this content"
            ]);
        }
        return $next($request);
    }
}
