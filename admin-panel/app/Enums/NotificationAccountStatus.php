<?php

namespace App\Enums;

enum NotificationAccountStatus : string
{
    case Pending = "pending";
    case Send = "send";
    case Resend = "resend";
    case Read = "read";

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
