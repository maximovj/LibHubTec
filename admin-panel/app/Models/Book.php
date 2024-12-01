<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Notifications\Notifiable;
use Illuminate\Database\Eloquent\Model;

class Book extends Model
{
    use HasFactory, Notifiable;

    protected $table = "books";
    protected $fillable = [
        'thumbnail',
        'title',
        'author',
        'summary',
        'description',
        'stock',
        'price'
    ];

    public function scopeStock($query)
    {
        return (int) $query->sum('stock');
    }

}
