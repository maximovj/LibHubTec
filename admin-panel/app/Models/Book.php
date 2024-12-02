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
        'cover',
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

    public function scopeResultSearch($query, string $search) :int
    {
        return (int) $query
            ->where('title', 'like', "%$search%")
            ->orWhere('author', 'like', "%$search%")
            ->orWhere('summary', 'like', "%$search%")
            ->count();
    }

}
