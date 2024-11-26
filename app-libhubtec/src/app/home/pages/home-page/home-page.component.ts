import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { MenuBarComponent } from "../../../shared/components/menu-bar/menu-bar.component";

import { ImagesPreviewsComponent } from "../../components/gallery-images/gallery-images.component";

@Component({
  standalone: true,
  styles: ``,
  templateUrl: "./home-page.component.html",
  imports: [
    CommonModule,
    RouterModule,
    MenuBarComponent,
    ImagesPreviewsComponent,
  ],
})
export class HomePage implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
