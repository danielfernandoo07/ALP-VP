<?php

namespace App\Http\Controllers;

use App\Models\Category;
use App\Http\Requests\StoreCategoryRequest;
use App\Http\Requests\UpdateCategoryRequest;

class CategoryController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $category = Category::all();
        return response()->json($category);
    }

    public function show($category_id)
    {
        $category = Category::where('id', $category_id)->get();
        return $category->loadMissing('content:id,headline,image,content_text,category_id,user_id');
    }
}
