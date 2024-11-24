<?php

namespace App\Models;

use App\Enums\NotificationAccountStatus;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use MoonShine\Models\MoonshineUser;

class NotificationAccount extends Model
{
    use HasFactory;

    /*
    |--------------------------------------------------------------------------
    | GLOBAL VARIABLES
    |--------------------------------------------------------------------------
    */

    protected $table = 'notification_accounts';
    // protected $primaryKey = 'id';
    // public $timestamps = false;
    protected $fillable = [
        "moonshine_user_id",
        "account_id",
        "subject",
        "content",
        "attach",
        "signature",
        "status",
        "send_email",
        "tags",
    ];
    // protected $hidden = [];
    // protected $dates = [];
    // protected $appends = []; // atributo o campo o columna => virtual
    // protected $dateFormat = "";
    // protected $casts = [];

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

    public function user()
    {
        return $this->belongsTo(MoonshineUser::class, "moonshine_user_id", "id");
    }

    public function account()
    {
        return $this->belongsTo(Account::class, "account_id", "id");
    }

    /*
    |--------------------------------------------------------------------------
    | SCOPES
    |--------------------------------------------------------------------------
    */

    /*
    |--------------------------------------------------------------------------
    | ACCESSORS
    |--------------------------------------------------------------------------
    */

    /**
     * Get the attributes that should be cast.
     *
     * @return array<string, string>
     */
    protected function casts()
    {
        return [
            "status" => NotificationAccountStatus::class,
            "tags" => "array",
        ];
    }

    /*
    |--------------------------------------------------------------------------
    | MUTATORS
    |--------------------------------------------------------------------------
    */

}
