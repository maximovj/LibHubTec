// Generated by https://quicktype.io

import { ApiResponse } from "./api-response.inteface";

export interface AccountDetailsResponse {
  response:   ApiResponse;
  data?:      AccountDetailsData[];
}

export interface AccountDetailsData {
  id:        number;
  name:      string;
  last_name: string;
  sex:       string;
  age:       string;
  grade:     string;
  shift:     string;
  photo:     null;
  bio:       string;
  username:  string;
  email:     string;
  password?:  string;
  createdAt: Date;
  updatedAt: Date;
}

