<?php

namespace Database\Seeders;

use App\Models\Announcement;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class AnnouncementSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Announcement::truncate();

        Announcement::factory()->create([
            'moonshine_user_id' => 1,
            'title' => 'Calendario Escolar',
            'content' =>'Hola, comunidad técnologica, conoce nuetros calendario escolar de este nuevo ciclo escolar.
                        Para ver más informació, haz click en el "Enlance externo".',
            'pictures' => ["announcements/3qLjSCcVi0XUCO6zIIL20PougoaBpCRf2dbYXVaA.jpg"],
            'link' =>  null,
            'tags' => 'Calendario,Ciclo Escolar',
            'is_published' => true,
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        Announcement::factory()->create([
            'moonshine_user_id' => 1,
            'title' => 'Nuestros instalaciones',
            'content' =>'Hola comunida, conoce nuestros instalacciones, para más información puede comunicarse al tel. 00-00-000-00
                        o desde nuestro página oficial de Facebook, accediendo dándole clic en "Enlace externo"',
            'pictures' => ["announcements/URIchL85VqTM7lEgn7dYFXb6Uw8gsTZezXMz7m99.jpg"],
            'link' =>  null,
            'tags' => 'Instalaciones',
            'is_published' => true,
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        //Announcement::factory(100)->create();
    }
}
