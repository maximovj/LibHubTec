import { AuthService } from './../../services/auth-service.service';
import { Component, inject, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";

@Component({
  templateUrl: './recover-auth.component.html',
  styles: ``,
  standalone: true,
  imports: [
    RouterModule,
  ],
})
export class RecoverAuthComponent implements OnInit {

  private authService = inject(AuthService);

  constructor() { }

  ngOnInit(): void {
    this.authService.resetAuthStatus();
  }

}
