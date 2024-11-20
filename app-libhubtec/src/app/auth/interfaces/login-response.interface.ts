// Generated by https://quicktype.io

import { ApiResponse } from "./api-response.inteface";

export interface LoginResponse {
  response: ApiResponse;
  data?:     LoginData | null;
}

export interface LoginData {
  token?:         string;
  refresh_token?: null;
}
