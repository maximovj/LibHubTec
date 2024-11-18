import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  standalone: true,
  templateUrl: './recover-account.component.html',
  styles: ``,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterModule,
    CheckboxModule,
    ButtonModule,
    InputTextModule,
  ],
})
export class RecoverAccountComponent implements OnInit {

  private fb = inject(FormBuilder);

  public myForm : FormGroup = this.fb.group({
    'email': ['', [Validators.required, Validators.email]],
    'password': ['' , [Validators.required]],
  });

  constructor() { }

  ngOnInit(): void { }

  public onSubmit() : void
  {

  }

}
