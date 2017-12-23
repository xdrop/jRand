Prism.languages.java = Prism.languages.extend('clike', {
    'keyword': /\b(?:abstract|continue|for|new|switch|assert|default|goto|package|synchronized|boolean|do|if|private|this|break|double|implements|protected|throw|byte|else|import|public|throws|case|enum|instanceof|return|transient|catch|extends|int|short|try|char|final|interface|static|void|class|finally|long|strictfp|volatile|const|float|native|super|while)\b/,
    'number': /\b0b[01]+\b|\b0x[\da-f]*\.?[\da-fp\-]+\b|\b\d*\.?\d+(?:e[+-]?\d+)?[df]?\b/i,
    'operator': {
        pattern: /(^|[^.])(?:\+[+=]?|-[-=]?|!=?|<<?=?|>>?>?=?|==?|&[&=]?|\|[|=]?|\*=?|\/=?|%=?|\^=?|[?:~])/m,
        lookbehind: true
    }
});

Prism.languages.insertBefore('java','function', {
    'jrand-class': {
        pattern: /^[A-Z][a-zA-Z]+(?=(\s+\w+\s*=\s*JRand\.\w+))/i
    },
    'jrand-trademark': {
        pattern: /\bJRand\b/
    },
    'jrand-string': {
        pattern: /\bString\b/
    },
    'string': {
    		pattern: /(["'])(?:\\[\s\S]|(?!\1)[^\\])*\1/,
    		greedy: true
	},
    'arrow': {
        pattern: /=>/
    },
    'jrand': {
        pattern: /(\b(?:bool|character|decimal|dbl|flt|string|paragraph|sentence|word|syllable|firstname|lastname|name)(?:\(\))?\.)\w+/i,
        lookbehind: true
    }
});

Prism.languages.insertBefore('java','function', {
    'annotation': {
        alias: 'punctuation',
        pattern: /(^|[^.])@\w+/,
        lookbehind: true
    }
});
