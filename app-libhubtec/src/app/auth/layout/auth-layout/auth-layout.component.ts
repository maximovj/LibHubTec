import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  imports: [
    RouterModule,
  ],
  styles: ``,
  templateUrl: './auth-layout.component.html',
})

export class LayoutAuthComponent implements OnInit {
  constructor() { }

  ngOnInit() { }
}
