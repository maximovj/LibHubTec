import { Component, OnInit } from '@angular/core';

@Component({
  standalone: true,
  imports: [],
  template: `
    <h1>Ver mis libros</h1>
    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Possimus minus inventore libero. Nisi minus nulla itaque esse voluptas modi accusantium nobis pariatur nihil distinctio est similique fugiat, a eos veritatis.</p>
  `,
})

export class UserBooksComponent implements OnInit {
  constructor() { }

  ngOnInit() { }
}
