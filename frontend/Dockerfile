FROM node As builder-front

WORKDIR /app

COPY . .
RUN npm install
RUN npm run build -dev

FROM nginx
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=builder-front /app/dist/frontend/ /usr/share/nginx/html

EXPOSE 80
