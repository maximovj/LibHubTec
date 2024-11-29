<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use MoonShine\Models\MoonshineUser;

class Search extends Model
{
    /** @use HasFactory<\Database\Factories\SearchFactory> */
    use HasFactory;

    /*
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    |
    | GLOBAL VARIABLES
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    */

    protected $table = 'searches';
    protected $fillable = [
        'account_id',
        'query',
        'base_url',
        'search',
        'result',
    ];
    //protected $appends = [];
    //protected $casts = [];
    //protected $dateFormat = "";
    //protected $dates = [];
    //protected $hidden = [];

    /*
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    |
    | FUNCTIONS
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    */

    /*
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    |
    | RELATIONS
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    */

    public function account()
    {
        return $this->belongsTo(Account::class, 'account_id', 'id');
    }

    /*
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    |
    | ACCESSORS
    | _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
    */

}
