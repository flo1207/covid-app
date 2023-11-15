FROM node As builder-front

WORKDIR /app

COPY /frontend/ .
RUN npm install
RUN npm run build -dev

FROM nginx
COPY --from=builder-front /app/dist/frontend/ /usr/share/nginx/html
COPY --from=builder-front /nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
