import { Pipe, PipeTransform } from '@angular/core';
import { environments } from '../../../environments/environments';

@Pipe({
  name: 'picture',
  standalone: true,
})

export class PicturePipe implements PipeTransform {

  private ENV_BASE_URL_STORAGE = environments.ENV_BASE_URL_STORAGE;

  transform(value: string | undefined | null): any {
    if(!value) {
      return 'images/no_thumbnail.jpg';
    }
    return `${this.ENV_BASE_URL_STORAGE}/storage/${value}`;
  }
}
