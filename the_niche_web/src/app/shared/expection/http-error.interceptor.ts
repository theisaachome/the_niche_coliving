import {HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {catchError, throwError} from "rxjs";

@Injectable()
export class  HttpErrorInterceptor implements HttpInterceptor{

    intercept(
        req: HttpRequest<any>,
        next: HttpHandler
    ){
        return next.handle(req).pipe(
            catchError((error: HttpErrorResponse) => {
                let errorMsg = '';

                if (error.error instanceof ErrorEvent) {
                    errorMsg = `Client-side error: ${error.error.message}`;
                } else {
                    errorMsg = `Server returned code ${error.status}, body was: ${JSON.stringify(error.error)}`;
                }

                console.error(errorMsg);

                return throwError(() => new Error(errorMsg));
            })
        );
    }

}
