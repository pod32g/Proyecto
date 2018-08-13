function CuestionarioViewModel(){
	var self = this;
	self.preguntas = ko.observableArray();
	self.tipo = ko.observable();
	self.codigo = ko.observable();
}