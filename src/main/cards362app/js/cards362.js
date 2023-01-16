
var cards362 = (function() {
	var opt = {
		table: '#card-table',
		rotatablePlane: '#rotatable-plane',
		fixedPlane: '#fixed-plane',
		cardsUrl: 'js/3rdparty/einaregilsson-cards-js/img/cards.png',
		playerRotation: 0
	};
	var items = {};

	function init(options) {
		if (options) {
			for (var i in options) {
				if (opt.hasOwnProperty(i)) {
					opt[i] = options[i];
				}
			}
		}
		cards.init({table: opt.table, cardsUrl: opt.cardsUrl});
	}

	function createCard(id, suit, rank) {
		//card = new cards.Card(suit, rank, $(opt.rotatablePlane));
		card = new cards.Card(suit, rank, cards.options.table);
		card.id = id;
		items[id] = card;
		return card;
	}

	function createDeck(id) {
		deck = new cards.Deck();
		deck.id = id;
		items[id] = deck;
	}

	function createPile(id, faceUp, x, y) {
		pile = new cards.Deck({faceUp:faceUp, x:x, y:y});
		pile.id = id;
		items[id] = pile;
	}

	function createButton(id, eventName, label, x, y) {
		button = new Button(id, eventName, label, x, y);
		items[id] = button;
		console.log(items);
	}

	function getById(id) {
		return items[id];
	}

	function setPlayerRotation(deg) {
		opt.playerRotation = deg;
		$('.player-rotated').css('transform', 'rotate('+deg+'deg)');
	}

	function setPlayerDisabled(disabled) {

	}

	function Button(id, eventName, label, x, y) {
		this.init(id, eventName, label, x, y);
	}
	
	Button.prototype = {
		init: function(id, eventName, label, x, y) {
			this.id = id;
			this.eventName = eventName;
			this.el = $('<button/>').css({
				width: 100,
				height: 30,
				left: x+'px',
				top: y+'px',
				position:'absolute',
			}).data('button', this)
			.html(label)
			.click(function() { doSend(JSON.stringify({event: eventName, id: id})); })
			.appendTo($(opt.fixedPlane));
		},
		setLocation: function(x, y) {
			$(this.el).css({left: x, top: y});
		},
		hide: function() {
			$(this.el).hide();
        },
        show: function() {
            $(this.el).show();
        }
	}		

	cards.Card.prototype.setFixed = function(fixed) {
		if (fixed) {
			this.el.detach().appendTo($(opt.fixedPlane))
		} else {
			this.el.detach().appendTo($(opt.rotatablePlane))
		}
	};

	cards.Card.prototype.setUpright = function(upright) {
		if (upright) {
			this.el.classList.add('player-upright')
		} else {
			this.el.classList.remove('player-upright')
		}
	};

	cards.Card.prototype.setFaceUp = function(faceUp) {
		if (faceUp) {
			this.showCard();
		} else {
			this.hideCard();
		}
	};

	cards.Card.prototype.setVisible = function(visible) {
		if (visible) {
			this.el.show();
		} else {
			this.el.hide();
		}
	};

	cards.Container.prototype.setFixed = function(fixed) {

	};

	cards.Container.prototype.setUpright = function(upright) {

	};

	cards.Container.prototype.setVisible = function(visible) {

	};

    cards.Container.prototype.addCardBottom = function(c) {
        this.addCardsBottom([c]);
    }

    cards.Container.prototype.addCardsBottom = function(cards) {
        for (var i = 0; i < cards.length;i++) {
            var card = cards[i];
            if (card.container) {
                card.container.removeCard(card);
            }
            this.unshift(card);
            card.container = this;
        }
    }

	return {
		options: opt,
		init: init,
		createCard: createCard,
		createDeck: createDeck,
		createPile: createPile,
		createButton: createButton,
		getById: getById,
		setPlayerRotation: setPlayerRotation,
		setPlayerDisabled: setPlayerDisabled,
	};
})();

if (typeof module !== 'undefined') {
    module.exports = cards362;
}
