<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ReserveBook extends Model
{
    use HasFactory;

    protected $fillable = [
        'account_username',
        'account_email',
        'account_name',
        'account_last_name',
        'book_title',
        'book_author',
        'book_count',
        'book_price',
        'account_id',
        'book_id',
        'start_date',
        'end_date',
        'status',
        'active',
    ];

    public function book()
    {
        return $this->belongsTo(Book::class, 'book_id', 'id');
    }

    public function account()
    {
        return $this->belongsTo(Account::class, 'account_id', 'id');
    }

    public function scopeIsActive($query)
    {
        return $query->where('active', true);
    }

    public function scopeBookPrice($query)
    {

        return $query->sum('book_price');
    }


}
