<?php

namespace App\Enums;

enum NotificationAccountStatus : string
{
    case Pending = "pendiente";
    case Send = "enviado";
    case Resend = "reenviado";
    case Read = "leído";

    public function toString(): ?string
    {
        return match ($this) {
            self::Pending => 'Pending',
            self::Send => 'Send',
            self::Resend => 'Resend',
            self::Read => 'Read',
        };
    }
}
