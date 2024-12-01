<?php

return [
    'profile' => 'Perfil',
    'dashboard' => 'Tablero',
    'add' => 'Agregar',
    'create' => 'Crear',
    'edit' => 'Editar',
    'show' => 'Mostrar',
    'save' => 'Guardar',
    'saved' => 'Guardado',
    'saved_error' => 'Error',
    'filters' => 'Filtros',
    'search' => 'Buscar',
    'reset' => 'Reiniciar',
    'total' => 'Total',
    'deleted' => 'Eliminado',
    'delete' => 'Eliminar',
    'deleting' => 'Eliminando',
    'download' => 'Descargar',
    'export' => 'Exportar',
    'import' => 'Importar',
    'cancel' => 'Cancelar',
    'confirm' => 'Confirmar',
    'close' => 'Cerrar',
    'confirm_message' => '¿Estás seguro?',
    'back' => 'Regresar',
    'more' => 'Más',
    'all_sections' => 'Todas las secciones',
    'file' => 'Archivo',
    'loading' => 'Cargando ...',
    'notfound' => 'Registros no encontrados',
    'collapse_menu' => 'Colapsar menú',
    '404' => 'Houston, tenemos un problema: página no encontrada',
    'copied' => '¡Copiado!',
    'notifications' => [
        'title' => 'Notificaciones',
        'mark_as_read_all' => 'Marcar todo como leído',
        'mark_as_read' => 'Marcar como leído',
    ],
    'login' => [
        'title' => '¡Bienvenido a :moonshine_title!',
        'description' => 'Por favor, inicia sesión en tu cuenta',
        'authorization' => 'Autorización',
        'remember_me' => 'Recuérdame',
        'login' => 'Iniciar sesión',
        'logout' => 'Cerrar sesión',
        'username' => 'Nombre de usuario',
        'email' => 'Correo electrónico',
        'password' => 'Contraseña',
        'or_socials' => 'o',
    ],
    'dasboard' => [
        'announcement' => [
            'title' => 'Anuncios',
            'is_published' => 'Publicados',
            'is_not_published' => 'No publicados',
        ],
        'book' => [
            'title' => 'Libros',
            'stock' => 'Libros disponibles',
            'reserve_book' => 'Libros reservados',
        ],
        'divider' => 'Otros',
        'metric' => [
            'account' => 'Cuentas',
            'recover_account' => 'Recuperación de cuentas',
            'notification' => 'Notificaciones',
            'search' => 'Búsquedas',
            'upcoming' => 'Próximos ingresos',
        ],
    ],
    'resource' => [
        'system' => 'Sistema',
        'role' => 'Roles',
        'name' => 'Nombre',
        'email' => 'Correo electrónico',
        'password' => 'Contraseña',
        'repeat_password' => 'Repetir contraseña',
        'avatar' => 'Avatar',
        'created_at' => 'Creado el',
        'admins_title' => 'Administradores',
        'role_title' => 'Roles',
        'role_name' => 'Título',

        // ! Mis Traducciones UI
        'book_title' => 'Libros',
        'book' => [
            'cover' => 'Portada',
            'title' => 'Título',
            'author' => 'Autor',
            'summary' => 'Resumen',
            'description' => 'Descripción',
            'stock' => 'Disponibilidad',
            'price' => 'Precio',
        ],
        'book_metric' => [
            'book_count' => 'Libros',
            'book_stock' => 'Libros disponibles',
        ],

        'account_tab_info_personal' => 'Información personal',
        'account_tab_user_details' => 'Detalles de la cuenta',
        'account_title' => 'Cuentas',
        'account' => [
            'name' => 'Nombres',
            'last_name' => 'Apellidos',
            'control_number' => 'Número de control',
            'sex' => 'Sexo',
            'age' => 'Edad',
            'grade' => 'Grado',
            'shift' => 'Turno',
            'photo' => 'Foto',
            'bio' => 'Bio',
            'username' => 'Nombre de usuario',
            'email' => 'Correo electrónico',
            'password' => 'Contraseña',
            'confirm_password' => 'Confirmar contraseña',
        ],

        'recover_account_title' => 'Recuperar cuentas',
        'recover_account' => [
            'account_name' => 'Nombre de usuario',
            'email' => 'Correo electrónico',
            'code' => 'Código',
            'token' => 'Token',
            'active' => 'Activo',
        ],

        'reserve_book_title' => 'Reservar libros',
        'reserve_book' => [
            'book_id' => 'Libro',
            'account_id' => 'Cuenta',
            'account_username' => 'Nombre de usuario',
            'account_email' => 'Correo electrónico',
            'account_name' => 'Nombre (s)',
            'account_last_name' => 'Apellido (s)',
            'book_title' => 'Título del libro',
            'book_author' => 'Autor del libro',
            'book_price' => 'Precio del libro',
            'book_count' => 'Cantidad de libro',
            'date_range' => 'Rango de fechas',
            'date_from' => 'Fecha desde',
            'date_to' => 'Fecha hasta',
            'status' => [
                '_' => 'Estado',
                'pending' => 'Pendiente',
                'processing' => 'Procesando',
                'paid' => 'Pagado',
                'debt' => 'Adeudo',
            ],
            'active' => 'Activa',
        ],

        'notification_account_title' => 'Notificar cuentas',
        'notification_account' => [
            'user' => 'Usuario',
            'account' => 'Cuenta',
            'subject' => 'Asunto',
            'content' => 'Contenido',
            'attach' => 'Archivo adjunto',
            'signature' => 'Firma comercial',
            'status' => 'Estado',
            'send_email' => 'Enviar correo electrónico',
            'tags' => 'Etiquetas',
        ],

        'announcement_title' => 'Anuncios',
        'announcement' => [
            'user' => 'Usuario',
            'title' => 'Título',
            'content' => 'Contenido',
            'link' => 'Enlace',
            'pictures' => 'Imágenes',
            'tags' => 'Etiquetas',
            'is_published' => 'Publicado',
        ],

        'search_title' => 'Búsquedas',
        'search' => [
            'account' => 'Cuenta',
            'query' => 'Consulta',
            'search' => 'Busqueda',
            'base_url' => 'Base URL',
            'result' => 'Resultado',
        ],

        'main_information' => 'Información principal',
        'change_password' => 'Cambiar contraseña',

        'link_socialite' => 'Vincular cuenta',
        'linked_socialite' => 'Cuenta vinculada',

        'queued' => 'En cola',

        'export' => [
            'exported' => 'Archivo exportado',
            'confirm_content' => 'Confirmar exportación de datos',
        ],

        'import' => [
            'imported' => 'Importado',
            'file_required' => 'El archivo es obligatorio',
            'extension_not_supported' => 'Extensión de archivo no soportada'
        ]
    ],
    'choices' => [
        'no_results' => 'No se encontraron resultados',
        'no_choices' => 'No hay opciones disponibles',
        'item_select' => 'Presiona para seleccionar',
        'unique_item' => 'Solo se pueden agregar valores únicos',
        'custom_add_item' => 'Solo se pueden agregar valores que coincidan con condiciones específicas',
        'add_item' => 'Presiona Enter para agregar ":value"',
        'max_item' => 'Solo se pueden agregar :count valores',
        'remove_item' => 'Eliminar elemento',
    ]
];
