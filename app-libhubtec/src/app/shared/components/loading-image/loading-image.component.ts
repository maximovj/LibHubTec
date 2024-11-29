import { CommonModule } from "@angular/common";
import { Component, Input, OnInit } from "@angular/core";
import { PicturePipe } from "../../pipes/picture.pipe";

@Component({
  selector: 'shared-loading-image',
  standalone: true,
  styles: ``,
  template: `
    <img
      [src]="imgSrc | picture"
      [class]="imgClass"
      [alt]="imgAlt"
      style="width: 100%; display: block;"
      (error)="srcError($event)" />
  `,
  imports: [
    CommonModule,
    PicturePipe,
  ]
})
export class LoadingImageComponent implements OnInit {

  @Input("imgSrc")
  public imgSrc ?:string | null = null;

  @Input("imgClass")
  public imgClass ?:string | null = null;

  @Input("imgAlt")
  public imgAlt ?:string | null = this.imgSrc;

  constructor () { }

  ngOnInit(): void {
    console.log('imgSRC: ',this.imgSrc)
  }

  public srcError(event: Event): void {
    const target = event.target as HTMLImageElement;
    target.src = 'images/no_thumbnail.jpg';
  }

}
