import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { MenuBarComponent } from "../../../shared/components/menu-bar/menu-bar.component";

import * as PrimeNGModule from "primeng";

@Component({
  standalone: true,
  styles: ``,
  templateUrl: "./home-page.component.html",
  imports: [
    CommonModule,
    RouterModule,
    MenuBarComponent,
  ],
})
export class HomePage implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
