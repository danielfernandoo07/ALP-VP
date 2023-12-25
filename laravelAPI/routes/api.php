<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ContentController;
use App\Http\Controllers\AuthenticationController;
use App\Http\Middleware\ContentAuthor;

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
Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});

Route::post('/login', [AuthenticationController::class,'login']);

Route::middleware(['auth:sanctum'])->group(
    function () {
        Route::get('/content', [ContentController::class, 'index']);
        Route::get('/content/{id}', [ContentController::class, 'show']);
        Route::get('/content1/{id}', [ContentController::class, 'show1']); //test
        Route::post('/content', [ContentController::class, 'create']);
        Route::patch('/content/{id}', [ContentController::class, 'update'])->middleware('content-author');
        Route::delete('/content/{id}', [ContentController::class, 'delete'])->middleware('content-author');
        Route::get('/logout', [AuthenticationController::class, 'logout']);
    }
);

