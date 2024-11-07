<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class Account extends Model
{

    use HasFactory, Notifiable;

    protected $fillable = [
        'name',
        'last_name',
        'control_number',
        'sex',
        'age',
        'grade',
        'shift',
        'photo',
        'bio',
        'username',
        'email',
        'password',
    ];



}
