import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { MenuBarComponent } from "../../../shared/components/menu-bar/menu-bar.component";

import { GalleryImagesComponent } from "../../components/gallery-images/gallery-images.component";
import { CardModule } from "primeng/card";

@Component({
  standalone: true,
  styles: ``,
  templateUrl: "./home-page.component.html",
  imports: [
    CommonModule,
    RouterModule,
    CardModule,
    MenuBarComponent,
    GalleryImagesComponent,
  ],
})
export class HomePage implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }

}
