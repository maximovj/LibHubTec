// Generated by https://quicktype.io

import { ApiResponse } from "../../shared/interfaces";

export interface VerifyTokenResponse {
  response: ApiResponse;
  data:     VerifyTokenData;
}

export interface VerifyTokenData {
  token:         string;
  refresh_token: string;
  is_valid:      boolean;
}
