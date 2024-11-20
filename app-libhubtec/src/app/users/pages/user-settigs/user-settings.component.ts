import { Component, inject, OnInit } from '@angular/core';

import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';

import { AuthService } from '../../../auth/services/auth-service.service';
import { User } from '../../../shared/interfaces';

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
