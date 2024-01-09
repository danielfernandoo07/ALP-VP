<?php

use App\Http\Controllers\PhotoController;
use App\Http\Controllers\UserImageController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Middleware\ContentAuthor;
use App\Http\Controllers\UserController;
use App\Http\Controllers\CommentController;
use App\Http\Controllers\ContentController;
use App\Http\Controllers\CategoryController;
use App\Http\Controllers\AuthenticationController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider and all of them will
| be assigned to the "api" middleware group. Make something great!
|
*/

//get specific user data
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});


//loginreg routes
Route::post('/login', [AuthenticationController::class,'login']);
Route::post('/register',[AuthenticationController::class, 'register']);
Route::get('/content/{id}/image', [ContentController::class, 'showImage']); //show image by content
Route::middleware(['auth:sanctum'])->group(
    function () {
        //user routes
        Route::get('/users', [UserController::class, 'getAllUser']); //show all users
        Route::patch('/user', [UserController::class, 'update']); //update current user
        Route::patch('/userpp', [UserController::class, 'updateImage']); //update current user image
        Route::post('/user/image', [UserImageController::class, 'create']); //create user image
        Route::get('/user/image', [UserController::class, 'getUserImage']); //get user image
        Route::delete('/user', [UserController::class, 'delete']); //delete current user (sbnre gaperlu tp gpp iseng bikin)
        Route::get('/user/{id}', [UserController::class, 'showSpecificOtherProfile']); //show specific user

        //category routes
        Route::get('/categories', [CategoryController::class, 'index']); //show specific category
        Route::get('/category/{id}', [CategoryController::class, 'show']); //show all categories

        //content routes
        Route::get('/contents', [ContentController::class, 'index']); //show all contents
        Route::get('/content/{id}', [ContentController::class, 'show']); //show specific content
        Route::get('/content1/{id}', [ContentController::class, 'showWithComments']); //show specific content with comments
        Route::post('/content', [ContentController::class, 'create']); //create content
        Route::patch('/content/{id}', [ContentController::class, 'update'])->middleware('content-author'); //update content
        Route::patch('/content/{id}/image', [ContentController::class, 'updateImage'])->middleware('content-author'); //update content image
        Route::delete('/content/{id}', [ContentController::class, 'delete'])->middleware('content-author'); //delete content
        Route::get('/user/contents/{userId}', [ContentController::class, 'contentsByUser']); //show all contents by user
       

        //comment routes
        Route::get('/comment', [CommentController::class, 'index']); //test
        Route::post('/comment', [CommentController::class, 'create']); //create comment
        Route::patch('/comment/{id}', [CommentController::class, 'update'])->middleware('comment-writer'); //update comment
        Route::delete('/comment/{id}', [CommentController::class, 'delete'])->middleware('comment-writer'); //delete comment
        Route::get('/content/{contentId}/comments', [CommentController::class, 'showCommentsByContent']); //show all comments by content

        //logout route
        Route::get('/logout', [AuthenticationController::class, 'logout']);
    }
    
);





