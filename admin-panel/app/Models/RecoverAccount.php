<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class RecoverAccount extends Model
{
    use HasFactory;

    protected $fillable = ['account_id', 'name', 'last_name', 'email', 'token', 'code', 'active'];

    public function accounts() {
        return $this->belongsTo(Account::class,'account_id','id');
    }

}
