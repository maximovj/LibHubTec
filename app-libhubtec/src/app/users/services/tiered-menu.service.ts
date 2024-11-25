import { computed, inject, Injectable, signal } from "@angular/core";
import { Router } from "@angular/router";

@Injectable({ providedIn: 'root' })
export class TieredMenuService {

  private router = inject(Router);

  public _routerLinkActive = signal<string>('settings');
  public routerLinkActive = computed(()=> this._routerLinkActive());

  public setActive(routerLink: string) :void
  {
    this._routerLinkActive.set(routerLink.replace('/', ''));
  }

  activeRouterLinkByUrl() :void
  {
    const url = this.router.url;
    const segments = url.split('/');
    const lastSegment = segments[segments.length - 1];
    this._routerLinkActive.set(lastSegment.replace('/', ''));
  }

}
