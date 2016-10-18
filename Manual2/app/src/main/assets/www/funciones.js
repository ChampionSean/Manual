function consulta(element){
var y = element.id;
var val = element.value;
var z = JSON.parse(Android.consulta2(val, y));
if(val != "Escoge"){

       switch(y){
       case "marca":
       var ele = document.getElementById("modelo");
       ele.disabled = false;
       for(var i = 0, len = z.length; i < len; ++i){
       var option = document.createElement("option");
        option.text = z[i];
       option.value = z[i];
        ele.add(option);
        }

       break;
       case "modelo":
       var ele = document.getElementById("version");
              ele.disabled = false;
              for(var i = 0, len = z.length; i < len; ++i){
              var option = document.createElement("option");
               option.text = z[i];
              option.value = z[i];
               ele.add(option);
               }
       break;

       case "version":
       var ele = document.getElementById("ano");
              ele.disabled = false;
              for(var i = 0, len = z.length; i < len; ++i){
              var option = document.createElement("option");
               option.text = z[i];
              option.value = z[i];
               ele.add(option);
               }
       break;

       case "ano":
       var ele = document.getElementById("posicion");
              ele.disabled = false;
              for(var i = 0, len = z.length; i < len; ++i){
              var option = document.createElement("option");
               option.text = z[i];
              option.value = z[i];
               ele.add(option);
               }
       break;

       case "posicion":
       var ele = document.getElementById("carga");
              ele.disabled = false;
              for(var i = 0, len = z.length; i < len; ++i){
              var option = document.createElement("option");
               option.text = z[i];
              option.value = z[i];
               ele.add(option);
               }
       break;

       case "carga":
       var ele = document.getElementById("ancho");
              ele.disabled = false;
              for(var i = 0, len = z.length; i < len; ++i){
              var option = document.createElement("option");
               option.text = z[i];
              option.value = z[i];
               ele.add(option);
               }
       break;

       case "ancho":
       var ele = document.getElementById("resultado");
              ele.value = "SERIE = 40 R   RIN 18  INDICE DE CARGA 92 RV V ANCHO 235";

       break;
       default:
       }
       }

    }

