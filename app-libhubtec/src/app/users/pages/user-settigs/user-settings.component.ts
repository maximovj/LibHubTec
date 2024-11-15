import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../../auth/services/auth-service.service';
import { User } from '../../../auth/interfaces';

@Component({
  standalone: true,
  imports: [],
  templateUrl: 'user-settings.component.html'
})

export class UserSettingsComponent implements OnInit {

  private service = inject(AuthService);

  constructor() { }

  ngOnInit() { }

  onClickLogout()
  {
    this.service.logout();
  }

  get user() : User | null
  {
    return this.service.user();
  }

}
