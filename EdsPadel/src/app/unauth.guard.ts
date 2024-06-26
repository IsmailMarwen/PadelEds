import { CanActivateFn } from '@angular/router';

export const unauthGuard: CanActivateFn = (route, state) => {
  const token = localStorage.getItem('jwt_token');

  if (!token) {
    return true; 
  } else {
   
    return false;
  }
};
