import { CommonModule } from "@angular/common";
import { Component, Input, OnInit } from "@angular/core";
import { CardModule } from "primeng/card";
import { GalleriaModule } from "primeng/galleria";
import { ImageModule } from "primeng/image";
import { ThumbnailPipe } from "../../../books/pipes/thumbnail.pipe";

@Component({
  selector: 'home-images-previes',
  standalone: true,
  styles: ``,
  templateUrl: "./gallery-images.component.html",
  imports: [
    CommonModule,
    CardModule,
    ImageModule,
    GalleriaModule,
    ThumbnailPipe,
  ]
})
export class GalleryImagesComponent implements OnInit {

  images: any[] | undefined;


  @Input("pictures") pictures :string[] = [];

  responsiveOptions: any[] = [
      {
          breakpoint: '1024px',
          numVisible: 5
      },
      {
          breakpoint: '768px',
          numVisible: 3
      },
      {
          breakpoint: '560px',
          numVisible: 1
      }
  ];

  constructor () { }

  ngOnInit(): void {
    this.images = this.pictures.map((x) => {
      return { itemImageSrc: x, alt: x  };
    });
  }

}
