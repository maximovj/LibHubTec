import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { CardModule } from "primeng/card";
import { GalleriaModule } from "primeng/galleria";
import { ImageModule } from "primeng/image";

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
  ]
})
export class GalleryImagesComponent implements OnInit {

  images: any[] | undefined;

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

  constructor () {
    this.images = [
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+1',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+1',
        alt: 'Description for Image 1',
        title: 'Title 1'
      },
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+2',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+2',
        alt: 'Description for Image 2',
        title: 'Title 2'
      },
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+3',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+3',
        alt: 'Description for Image 3',
        title: 'Title 3'
      },
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+4',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+4',
        alt: 'Description for Image 4',
        title: 'Title 4'
      },
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+5',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+5',
        alt: 'Description for Image 5',
        title: 'Title 5'
      },
      {
        itemImageSrc: 'https://placehold.co/300x200?text=Imagen+del+evento+%20+6',
        thumbnailImageSrc: 'https://placehold.co/60x60?text=Imagen+del+evento+%20+6',
        alt: 'Description for Image 6',
        title: 'Title 6'
      },
    ];
  }

  ngOnInit(): void {

  }

}
