import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/services/auth-service.service';
import { User } from '../../../auth/interfaces';

import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { InputTextareaModule } from 'primeng/inputtextarea';

@Component({
  standalone: true,
  imports: [
    InputTextareaModule,
    InputTextModule,
    ButtonModule,
  ],
  templateUrl: 'user-settings.component.html'
})

export class UserSettingsComponent implements OnInit {

  private service = inject(AuthService);

  constructor() { }

  ngOnInit() { }

  get user() : User | null
  {
    return this.service.user();
  }

}
