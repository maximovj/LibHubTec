<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use MoonShine\Models\MoonshineUser;

class Announcement extends Model
{
    use HasFactory;

    /*
    |--------------------------------------------------------------------------
    | GLOBAL VARIABLES
    |--------------------------------------------------------------------------
    */

    protected $table = "announcements";
    protected $fillable = [
        "moonshine_user_id",
        "title",
        "content",
        "pictures",
        "link",
        "deleted",
        "is_published",
    ];
    //protected $dates = [];
    //protected $hidden =[];
    //protected $cats = [];
    //protected $dateFormat = "";
    //protected $appends = [];

    /*
    |--------------------------------------------------------------------------
    | FUNCTIONS
    |--------------------------------------------------------------------------
    */

    /*
    |--------------------------------------------------------------------------
    | RELATIONS
    |--------------------------------------------------------------------------
    */

    public function moonshine_user()
    {
        return $this->belongsTo(MoonshineUser::class, 'moonshine_user_id', 'id');
    }

    /*
    |--------------------------------------------------------------------------
    | SCOPES
    |--------------------------------------------------------------------------
    */

    public function scopeIsPublished($query)
    {
        return $query->where('is_published', true);
    }

    public function scopeIsNotPublished($query)
    {
        return $query->where('is_published', false);
    }

    /*
    |--------------------------------------------------------------------------
    | ACCESSORS
    |--------------------------------------------------------------------------
    */

    public function casts()
    {
        return [
            "pictures" => "array"
        ];
    }

    /*
    |--------------------------------------------------------------------------
    | MUTATORS
    |--------------------------------------------------------------------------
    */

}
