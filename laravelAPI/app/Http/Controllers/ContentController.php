<?php

namespace App\Http\Controllers;

use App\Models\Content;
use App\Http\Requests\StoreContentRequest;
use App\Http\Requests\UpdateContentRequest;
use App\Http\Resources\ContentResource;

class ContentController extends Controller
{
    /**
     * Display a listing of the resource.
     */
    public function index()
    {
        $content = Content::all();
        // return response()->json($content);
        return ContentResource::collection($content);
    }

    /**
     * Show the form for creating a new resource.
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     */
    public function store(StoreContentRequest $request)
    {
        //
    }

    /**
     * Display the specified resource.
     */
    public function show($id)
    {
        $content = Content::findOrFail($id);
        return response()->json($content);
    }

    /**
     * Show the form for editing the specified resource.
     */
    public function edit(Content $content)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     */
    public function update(UpdateContentRequest $request, Content $content)
    {
        //
    }

    /**
     * Remove the specified resource from storage.
     */
    public function destroy(Content $content)
    {
        //
    }
}
