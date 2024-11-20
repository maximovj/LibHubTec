export interface RecoverAccountRequest
{
  code?:              string | null;
  new_password?:      string | null;
  confirm_password?:  string | null;
}
