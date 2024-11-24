import { CommonModule } from "@angular/common";
import { Component, Input } from "@angular/core";
import { ProgressSpinnerModule } from "primeng/progressspinner";

@Component({
  selector: 'shared-loading',
  standalone: true,
  templateUrl: "./loading.component.html",
  styles: ``,
  imports: [
    CommonModule,
    ProgressSpinnerModule,
  ],
})
export class LoadingComponent {

  @Input("text")
  public text : string = "";

  constructor() {

  }

}
