<?php

namespace Database\Seeders;

use App\Models\Book;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class BookSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        Book::truncate();

        Book::factory()->create([
            'cover' => 'books/09THoOYSm2A2ijZkEubcbtc2nvHVzER4C6vX29sO.jpg',
            'title' => 'El ciberactivismo',
            'author' => 'Mario Táscon y Yolanda Quintana',
            'summary' => 'Se extiende por el mundo una nueva forma de activismo social. En los últimos años hemos presenciado las revueltas árabes, las filtraciones de secretos de las embajadas de EE UU y los gobiernos de todo el mundo a través de Wikileaks, las acciones del colectivo de ciberatacantes Anonymous contra compañías como Visa o Amazon o la campaña electoral basada en redes sociales que llevó a Obama a la presidencia de Estados Unidos. Con la popularización de las redes sociales la gente tiene a su alcance unas poderosas herramientas para protestar contra los gobiernos, los políticos o las grandes empresas. Lo que hasta hace escasos años era privilegio de los pocos expertos que manejaban Internet, se está democratizando con las nuevas herramientas y la simplificación de la capacidad de emitir mensajes y relacionarse rápidamente con otras personas, sin importar la edad, el sexo, la religión o el lugar del mundo en el que se encuentran. Cualquiera puede ser un cabecilla o puede apoyar con facilidad un movimiento, la difusión de una idea que comparta o con la que no esté de acuerdo. Mientras, los poderes clásicos asisten con estupefacción a un mundo en el que el “control” sobre los clientes, lectores, espectadores y trabajadores se les escapa de las manos.',
            'description' => 'Es un libro genial, tipo manual para las personas activistas',
            'stock' => 6,
            'price' => 155.0,
            'created_at' => now(),
            'updated_at' => now(),
        ]);

        Book::factory()->create([
            'cover' => 'books/UStnSI0BBQvo17lwHUzjhX6f3n91u94VYPBXip8F.jpg',
            'title' => 'El Aventurero',
            'author' => 'Santiago Gárcia-Clairac',
            'summary' => 'Víctor Latienza tiene quince años cuando descubre un nuevo personaje de tebeos que se titula El Capitán Trueno. Sus dibujos le deslumbran y le despiertan el deseo de ser dibujante de viñetas.',
            'description' => 'Es una gran libro, que toca temas sensibles para la época como la derrota y la homosexualidad.',
            'stock' => 6,
            'price' => 170.0,
        ]);

        Book::factory()->create([
            'cover' => 'books/kBctfC7QVQJUQxmjj0fqHDu2kTt7ZCJ27oMBNH7w.jpg',
            'title' => 'El gato negro',
            'author' => 'Edgar Allan Poe',
            'summary' => 'El gato negro (título original en inglés: The Black Cat) es un cuento de horror del escritor estadounidense Edgar Allan Poe, publicado en el periódico Saturday Evening Post de Filadelfia en su número del 19 de agosto de 1843. La crítica lo considera uno de los más espeluznantes de la historia de la literatura.',
            'description' => 'Es una historia corta, y sencilla.',
            'stock' => 5,
            'price' => 65.0,
        ]);

        Book::factory()->create([
            'cover' => 'books/QNrdOQl8sV9EKqFtg76yQn9KEOWyjtPXNqFD5U4e.jpg',
            'title' => 'El orfanato heiskinn',
            'author' => 'Javier Berzosa',
            'summary' => 'El joven sueco Isak Berg, se ve obligado a ingresar en el Orfanato Heskinn tras la dolorosa pérdida de sus padres a manos de dos individuos. Solitario, tras los valles de la isla sueca de Gotland (Visby), se encuentra el peculiar edificio, construido en el siglo XVIII por altos nobles de la isla. Cuando llega al deteriorado y escalofriante lugar, comienzan a suceder hechos macabros que parecen tener una conexión entre ellos. Descubrirá secretos del pasado de los trabajadores y compañeros que parecen querer esconder algo más de lo que muestran. Pero su inseguridad aumentará cuando conozca la leyenda de un chico llamado Börje Persson, que pareció haber asesinado a dos menores en algún momento de su vida, y la extraña presencia de notas en el lavabo, escritas a mano incoherentemente. ¿Hasta dónde estarías dispuesto a llegar para sobrevivir?',
            'description' => 'Es un libro aterrador con misterios para resolver.',
            'stock' => 2,
            'price' => 210.0,
        ]);

        Book::factory()->create([
            'cover' => 'books/YuM5TM1RT0rAYsOhgY8WDZabm5nUWdj8xvL21o3F.jpg',
            'title' => 'Lo que callan los muertos',
            'author' => 'Ana Elena Rivera',
            'summary' => 'Gracia San Sebastián ha renunciado a una exitosa carrera laboral en Nueva York y ha regresado junto a su marido Jorge a su Oviedo natal para ejercer de investigadora de fraudes a la Seguridad Social. Su nuevo caso está relacionada con el cobro de la pensión de un militar franquista que sobrepasa los ciento doce años, cifra a todas luces sospechosa. Mientras su vida personal avanza por sendas imprevistas, Gracia se encontrará con ramificaciones del caso que la llevarán a investigar el suicidio de una vecina de su madre. De vez en cuando pide consejo a una buena amiga de la familia, la monja dominica sor Florencia.',
            'description' => 'Desconocido',
            'stock' => 6,
            'price' => 180.0,
        ]);

        Book::factory()->create([
            'cover' => 'books/5fPNLQGJKLZqsC003EjR15KEtHhtxQ1fMDrnDrsF.jpg',
            'title' => 'Un mundo peor',
            'author' => 'Claudio Cerdán',
            'summary' => 'Roberto Cusac, expolicia reciclado a detective, alcoholizado y solitario, vive obsesionado por un caso que destrozó su carrera, su matrimonio y su alma: la desparición de su hijo de 6 años, Jaime, al que nunca encontró. Ha repasado mil veces las pistas y siempre le llevan a ninguna parte. Cuando le encargan que busque a una chica desaparecida, sus heridas parecen reabrirse, pero un halo de esperanza y la sensación difusa de que el destino le brinda una segunda oportunidad avivan de nuevo su instinto para jugar una última partida a doble o nada…',
            'description' => 'Desconocido',
            'stock' => 1,
            'price' => 330.0,
        ]);

        //Book::factory(20)->create();
    }
}
