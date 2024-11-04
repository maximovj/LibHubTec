import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  standalone: true,
  imports: [
    RouterModule
  ],
  template: `
    <router-outlet></router-outlet>
  `,
})

export class LayoutBookComponent implements OnInit {
  constructor() { }

  ngOnInit() { }
}
